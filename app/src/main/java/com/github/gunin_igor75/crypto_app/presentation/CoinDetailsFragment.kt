package com.github.gunin_igor75.crypto_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.crypto_app.databinding.FragmentCoinDetailsBinding
import com.squareup.picasso.Picasso

class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentCoinDetailsBinding? = null

    val binding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailsBinding is null")

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        val args = requireArguments()
        val fSym =  args.getString(EXTRA_SYMBOL)
        if (fSym == null) {
            requireActivity().finish()
        } else {
            viewModel.loadDetailsCoin(fSym).observe(viewLifecycleOwner) {
                binding.textViewFSymbol.text = it.fromSymbol
                binding.textViewToSymbol.text = it.toSymbol
                binding.textViewPrice.text = it.price.toString()
                binding.textViewMin.text = it.lowDay.toString()
                binding.textViewMax.text = it.highDay.toString()
                binding.textViewLastDeal.text = it.lastMarket
                binding.textViewLastTime.text = it.lastUpdate
                Picasso.get().load(it.imageUrl).into(binding.imageViewCoinDetails)
            }
        }
    }

    companion object {
        private const val EXTRA_SYMBOL = "fSym"
        fun newInstanceCoinDetails(fSym: String): CoinDetailsFragment {
            return CoinDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SYMBOL, fSym)
                }
            }
        }
    }
}