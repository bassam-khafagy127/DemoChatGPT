package com.megatrust.demochatgpt.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.megatrust.demochatgpt.R
import com.megatrust.demochatgpt.adapters.MessageAdapter
import com.megatrust.demochatgpt.data.Message
import com.megatrust.demochatgpt.databinding.ActivityMainBinding
import com.megatrust.demochatgpt.viewmodels.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
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

    }


    private fun addCallBacks() {
        binding.apply {
            sendButtonIv.setOnClickListener {
                val query = queryEt.text.toString()
                if (query.trim().isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.getApiAnswer(query)
                    }
                    messageList.add(Message(query, Message.SENT_BY_ME))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Query Field can't be null",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    }
}