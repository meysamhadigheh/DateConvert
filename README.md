# DateConvert

This library helps you to make converting different date to each other

hijri to gregoria and jalali

gregoria to hijri and jalali

jalali to gregoria and hijri

............................................................................

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.meysamhadigheh:DateConvert:c6f5273a58'
	}
  
How to use it as sample

        var date = CalendarHelper().jalaliToGregorian(1398, 04, 13)

