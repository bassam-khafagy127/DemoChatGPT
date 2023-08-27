package com.megatrust.demochatgpt.activites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.megatrust.demochatgpt.adapters.MessageAdapter
import com.megatrust.demochatgpt.data.Message
import com.megatrust.demochatgpt.databinding.ActivityMainBinding
import com.megatrust.demochatgpt.utills.Resource
import com.megatrust.demochatgpt.viewmodels.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<QuestionsViewModel>()

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addCallBacks()

        messageList = ArrayList()
        messageAdapter = MessageAdapter(messageList)

        val layoutManager = LinearLayoutManager(applicationContext)
        binding.apply {
            messageRecyclerView.layoutManager = layoutManager
            messageRecyclerView.adapter = messageAdapter
        }

        viewModel.completionLiveData.observe(this) {
            when (it) {

                is Resource.Error -> {
                    binding.apply {
                        progressCircular.visibility = View.GONE
                        imageViewError.visibility = View.VISIBLE
                        Toast.makeText(applicationContext,it.message,Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    binding.apply {
                        progressCircular.visibility = View.VISIBLE
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        progressCircular.visibility = View.GONE
                        imageViewError.visibility = View.GONE

                    }
                    it.data?.let { nonNull ->
                        messageList.add(nonNull)
                        messageAdapter.notifyDataSetChanged()
                    }
                }

                is Resource.Unspecified -> {

                }
            }
        }

    }


    private fun addCallBacks() {
        binding.apply {

            queryLayout.setEndIconOnClickListener {
                Toast.makeText(applicationContext, queryLayout.editText?.text, Toast.LENGTH_LONG)
                    .show()

                val query = queryLayout.editText?.text.toString()

                query.let {
                    messageList.add(Message(it, Message.SENT_BY_ME))
                    messageAdapter.notifyDataSetChanged()
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.getApiAnswer(it)
                    }
                }
            }
        }

    }
}