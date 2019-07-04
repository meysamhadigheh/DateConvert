package info.meysam.dateconvertsample

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import hivatec.ir.hivatectools.activityHelpers.ParentActivity
import info.meysam.dateconvert.CalendarHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 */
class MainActivity : ParentActivity() {

    var ch = CalendarHelper()
    val days = ArrayList<Int>()
    var years = ArrayList<Int>()

    var typeface: Typeface? = null


    override fun refreshContent() {

    }

    override fun instantiateViews() {
        for (i in 1..31)
            days.add(i)
        dayPicker.data = days

        dayPicker.selectedItemPosition = 0

        typeface = Typeface.createFromAsset(context.assets, "fonts/shabnam_farsi.ttf")

        yearPicker.typeface = typeface
        monthPicker.typeface = typeface
        dayPicker.typeface = typeface


    }

    override fun prepareToolbar() {

    }

    override fun getIntentData() {

    }

    override fun setContentViewActivity() {

        setContentView(R.layout.activity_main)


    }

    override fun setActivityContent() {

        jalali.callOnClick()


    }

    override fun setViewListeners() {


        jalali.setOnClickListener {

            dateSelectorListener(jalali)

        }
        hijri.setOnClickListener {

            dateSelectorListener(hijri)

        }
        gregorian.setOnClickListener {

            dateSelectorListener(gregorian)

        }



        dayPicker.setOnItemSelectedListener { _, _, position ->

            val year = this.years[yearPicker.currentItemPosition]
            val month = monthPicker.currentItemPosition + 1
            val day = position + 1

            dateConverter(year, month, day)

        }
        monthPicker.setOnItemSelectedListener { _, _, position ->

            val year = this.years[yearPicker.currentItemPosition]
            val month = position + 1
            val day = dayPicker.currentItemPosition + 1

            dateConverter(year, month, day)


        }
        yearPicker.setOnItemSelectedListener { _, _, position ->

            val year = this.years[position]
            val month = monthPicker.currentItemPosition + 1
            val day = dayPicker.currentItemPosition + 1

            dateConverter(year, month, day)

        }

    }

    private fun dateConverter(year: Int, month: Int, day: Int) {

        when {

            jalali.background != null -> setJalali(year, month, day)
            hijri.background != null -> setHijri(year, month, day)
            gregorian.background != null -> setGregorian(year, month, day)

        }

    }


    private fun dateSelectorListener(textView: TextView?) {

        hijri.background = null
        gregorian.background = null
        jalali.background = null

        jalali!!.setTextColor(ContextCompat.getColor(context, R.color.White))
        gregorian!!.setTextColor(ContextCompat.getColor(context, R.color.White))
        hijri!!.setTextColor(ContextCompat.getColor(context, R.color.White))



        textView!!.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        textView.setBackgroundResource(R.drawable.bg_date_selected)

        jalaliLayout.visibility = View.VISIBLE
        gregorianLayout.visibility = View.VISIBLE
        hijriLayout.visibility = View.VISIBLE

        when (textView.id) {

            R.id.jalali -> {

                jalaliLayout.visibility = View.GONE
                jalaliWheels()

            }
            R.id.hijri -> {
                hijriLayout.visibility = View.GONE
                hijriWheels()
            }

            R.id.gregorian -> {

                gregorianLayout.visibility = View.GONE
                gregorianWheels()

            }

        }


    }

    private fun gregorianWheels() {

        val years = ArrayList<Int>()
        for (i in 1970..2070)
            years.add(i)

        initWheels(years, ch.getArrayList(ch.monthsGregorian), "gregorian")

    }

    private fun hijriWheels() {

        val years = ArrayList<Int>()
        for (i in 1390..1490)
            years.add(i)

        initWheels(years, ch.getArrayList(ch.monthsHijri), "hijri")

    }

    private fun jalaliWheels() {

        val years = ArrayList<Int>()
        for (i in 1340..1450)
            years.add(i)

        initWheels(years, ch.getArrayList(ch.monthsJalali), "jalali")

    }

    private fun initWheels(years: ArrayList<Int>, month: ArrayList<String>, selector: String) {

        yearPicker.data = years
        this.years = years

        yearPicker.selectedItemPosition = 0


        monthPicker.data = month.subList(0, 12)
        monthPicker.selectedItemPosition = 0

        dayPicker.selectedItemPosition = 0


        when (selector) {

            "jalali" -> setJalali(years[0], 1, 1)
            "hijri" -> setHijri(years[0], 1, 1)
            "gregorian" -> setGregorian(years[0], 1, 1)

        }


    }

    @SuppressLint("SetTextI18n")
    private fun setJalali(year: Int, month: Int, day: Int) {

        val gd = ch.jalaliToGregorian(year, month, day)
        val hd = ch.jalaliToHijri(year, month, day)

        gregorianDate.text = "${gd.year} ${gd.month} ${gd.day}"
        hijriDate.text = "${hd.year} ${hd.month} ${hd.day}"


    }

    @SuppressLint("SetTextI18n")
    private fun setHijri(year: Int, month: Int, day: Int) {

        val gd = ch.hijriToGregorian(year, month, day)
        val jd = ch.hijriToJalali(year, month, day)

        gregorianDate.text = "${gd.year} ${gd.month} ${gd.day}"
        jalaliDate.text = "${jd.year} ${jd.month} ${jd.day}"


    }

    @SuppressLint("SetTextI18n")
    private fun setGregorian(year: Int, month: Int, day: Int) {


        val jd = ch.gregorianToJalali(year, month, day)
        val hd = ch.gregorianToHijri(year, month, day)

        jalaliDate.text = "${jd.year} ${jd.month} ${jd.day}"
        hijriDate.text = "${hd.year} ${hd.month} ${hd.day}"


    }


}
