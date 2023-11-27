package com.github.gunin_igor75.crypto_app.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.crypto_app.CryptoApp
import com.github.gunin_igor75.crypto_app.databinding.FragmentCoinDetailsBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentCoinDetailsBinding? = null

    private val binding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailsBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private val component by lazy {
        (requireActivity().application as CryptoApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        val fSym = getParam()
        viewModel.loadDetailsCoin(fSym).observe(viewLifecycleOwner) {
            with(binding) {
                textViewFSymbol.text = it.fromSymbol
                textViewToSymbol.text = it.toSymbol
                textViewPrice.text = it.price.toString()
                textViewMin.text = it.lowDay.toString()
                textViewMax.text = it.highDay.toString()
                textViewLastDeal.text = it.lastMarket
                textViewLastTime.text = it.lastUpdate
                Picasso.get().load(it.imageUrl).into(imageViewCoinDetails)
            }
        }
    }

    private fun getParam(): String {
        val args = requireArguments()
        return args.getString(EXTRA_SYMBOL, EMPTY)
    }

    companion object {
        private const val EXTRA_SYMBOL = "fSym"
        private const val EMPTY = ""

        fun newInstanceCoinDetails(fSym: String): CoinDetailsFragment {
            return CoinDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SYMBOL, fSym)
                }
            }
        }
    }
}