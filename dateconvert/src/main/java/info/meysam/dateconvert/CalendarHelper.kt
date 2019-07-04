package info.meysam.dateconvert

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import ir.huri.jcal.JalaliCalendar
import java.util.*

class CalendarHelper {

    constructor()

    val monthsJalali: HashMap<Int, String> = hashMapOf(
        1 to "فروردین",
        2 to "اردیبهشت",
        3 to "خرداد",
        4 to "تیر",
        5 to "مرداد",
        6 to "شهریور",
        7 to "مهر",
        8 to "آبان",
        9 to "آذر",
        10 to "دی",
        11 to "بهمن",
        12 to "اسفند",
        13 to "فروردین"
    )
    val monthsGregorian: HashMap<Int, String> = hashMapOf(
        1 to "January",
        2 to "February",
        3 to "March",
        4 to "April",
        5 to "May",
        6 to "June",
        7 to "July",
        8 to "August",
        9 to "September",
        10 to "October",
        11 to "November",
        12 to "December",
        13 to "January"
    )
    val monthsHijri: HashMap<Int, String> = hashMapOf(
        1 to "محرم",
        2 to "صفر",
        3 to "ربیع\u200Cالاول",
        4 to "ربیع\u200Cالثانی",
        5 to "جمادی\u200Cالاول",
        6 to "جمادی\u200Cالثانی",
        7 to "رجب",
        8 to "شعبان",
        9 to "رمضان",
        10 to "شوال",
        11 to "ذیقعده",
        12 to "ذیحجه",
        13 to "محرم"
    )
    val daysHijri: HashMap<Int, String> = hashMapOf(
        1 to "السبت",
        2 to "الأحد",
        3 to "الاثنين",
        4 to "الثلاثاء",
        5 to "الأربعاء",
        6 to "الخميس",
        7 to "الجمعة",
        8 to "السبت"
    )
    val daysJalali: HashMap<Int, String> = hashMapOf(
        1 to "شنبه",
        2 to "یکشنبه",
        3 to "دوشنبه",
        4 to "سه\u200Cشنبه",
        5 to "چهارشنبه",
        6 to "پنجشنبه",
        7 to "جمعه",
        8 to "شنبه"

    )
    val daysGregorian: HashMap<Int, String> = hashMapOf(
        1 to "Saturday",
        2 to "Sunday",
        3 to "Monday",
        4 to "Tuesday",
        5 to "Wednesday",
        6 to "Thursday",
        7 to "Friday",
        8 to "Saturday"

    )


    fun getArrayList(hashMap: HashMap<Int, String>): ArrayList<String> {

        val list = ArrayList<String>()

        for (i in 1..hashMap.size) {
            list.add(hashMap[i].toString())
        }

        return list


    }

    fun jalaliToGregorian(
        selectedYear: Int,
        selectedMonth: Int,
        selectedDay: Int
    ): Date {

        val gc = JalaliCalendar(selectedYear, selectedMonth, selectedDay).toGregorian()


        return Date(gc.get(Calendar.YEAR), (gc.get(Calendar.MONTH) + 1), gc.get(Calendar.DAY_OF_MONTH))


    }

    fun jalaliToHijri(selectedYear: Int, selectedMonth: Int, selectedDay: Int): Date {

        val gc = JalaliCalendar(selectedYear, selectedMonth, selectedDay).toGregorian()
        val hc = UmmalquraCalendar()
        hc.time = gc.time


        return Date(hc.get(Calendar.YEAR), (hc.get(Calendar.MONTH) + 1), hc.get(Calendar.DAY_OF_MONTH))


    }

    fun gregorianToHijri(selectedYear: Int, selectedMonth: Int, selectedDay: Int): Date {

        val gc = GregorianCalendar(selectedYear, selectedMonth - 1, selectedDay)

        val hc = UmmalquraCalendar()
        hc.time = gc.time


        return Date(hc.get(Calendar.YEAR), (hc.get(Calendar.MONTH) + 1), hc.get(Calendar.DAY_OF_MONTH))


    }


    fun gregorianToJalali(selectedYear: Int, selectedMonth: Int, selectedDay: Int): Date {


        val jc = JalaliCalendar(GregorianCalendar(selectedYear, selectedMonth - 1, selectedDay))

        return Date(jc.year, jc.month, jc.day)

    }

    fun hijriToGregorian(selectedYear: Int, selectedMonth: Int, selectedDay: Int): Date {

        val hc = UmmalquraCalendar(selectedYear, selectedMonth - 1, selectedDay)
        val gc = GregorianCalendar()
        gc.time = hc.time



        return Date(gc.get(Calendar.YEAR), (gc.get(Calendar.MONTH) + 1), gc.get(Calendar.DAY_OF_MONTH))


    }

    fun hijriToJalali(selectedYear: Int, selectedMonth: Int, selectedDay: Int): Date {

        val hc = UmmalquraCalendar(selectedYear, selectedMonth - 1, selectedDay)
        val gc = GregorianCalendar()
        gc.time = hc.time

        val jc = JalaliCalendar(gc)


        return Date(jc.year, jc.month, jc.day)


    }


}