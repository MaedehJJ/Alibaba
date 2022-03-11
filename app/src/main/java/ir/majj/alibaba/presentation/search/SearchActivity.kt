package ir.majj.alibaba.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import ir.majj.alibaba.R
import ir.majj.alibaba.databinding.ActivitySearchBinding
import ir.majj.alibaba.intentFor
import org.joda.time.LocalDate

class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            binding?.apply {
                val from = fromField.text.toString()
                val to = toField.text.toString()
                search.isEnabled = from.isNotEmpty() && to.isNotEmpty()
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            fromField.addTextChangedListener(textWatcher)
            toField.addTextChangedListener(textWatcher)
            toolbar.back.setOnClickListener { onBackPressed() }
        }
        setUpDatePicker()
        setUpDestination()
    }

    private fun setUpDestination() {
        binding?.apply {
            val destination = intent.getStringExtra(EXTRA_DESTINATION)
            if (destination != null && destination.isNotEmpty()) {
                toField.setText(destination)
            }
        }
    }

    private fun setUpDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.search_datePickerTitle))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(INPUT_MODE_CALENDAR).build()

        binding?.apply {
            chooseDate.setOnClickListener {
                it.isEnabled = false
                datePicker.show(supportFragmentManager, DATE_PICKER_TAG)
            }

            datePicker.addOnPositiveButtonClickListener {
                val date = LocalDate(it)
                chosenDate.text = getString(
                    R.string.search_dateFormat,
                    date.dayOfMonth.toString(),
                    date.monthOfYear().asShortText,
                    date.year.toString()
                )
                chosenDate.visibility = View.VISIBLE
                chooseDate.isEnabled = true
                datePicker.dismiss()
            }
            datePicker.addOnDismissListener { chooseDate.isEnabled = true }
        }
    }

    companion object {
        private const val EXTRA_DESTINATION = "SearchActivity:Destination"
        private const val DATE_PICKER_TAG = "SearchActivity:DatePicker"

        fun getOpenIntent(context: Context, description: String): Intent {
            val intent = context.intentFor<SearchActivity>()
            intent.putExtra(EXTRA_DESTINATION, description)
            return intent
        }
    }
}
