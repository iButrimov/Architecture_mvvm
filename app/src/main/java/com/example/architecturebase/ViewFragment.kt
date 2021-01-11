package com.example.architecturebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentViewBinding
import com.example.architecturebase.network.model.Post

class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val mainViewModel by viewModels<MainViewModel>()

    private val mainAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postObserver = Observer<List<Post>?> {
            mainAdapter.items = it
            binding.listSRL.isRefreshing = false

            if (it == null) {
                Toast.makeText(context, "list is empty", Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.post.observe(viewLifecycleOwner, postObserver)

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }

        mainViewModel.getPost()

        binding.listSRL.setOnRefreshListener {
            mainAdapter.items = emptyList()
            mainViewModel.getPost()
        }
    }
}