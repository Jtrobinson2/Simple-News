# Simple News App

> An "simple" news application built using MVVM architecture.

## Version
![Version](https://img.shields.io/badge/Version-1.1-blue)

## Components used in the app.
- [Java](https://www.java.com/en/) - As the programming language.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Lifecycle aware Observable data holder class.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - To manage the UI-related data in a lifecycle conscious way.
- [Retrofit2](https://square.github.io/retrofit/) - For making network calls.
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) - For displaying a scrollable list of articles.
- [CardView](https://developer.android.com/guide/topics/ui/layout/cardview) - For polishing the RecyclerView UI.
- [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) - To allow users to scroll through different news categories.

## Description
The Simple News App is a small project that fetches and parses JSON from the [News API](https://newsapi.org/). This app is built using MVVM architecture, and uses [Retrofit2](http://square.github.io/retrofit/) for making API calls
as well as [Viewpager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) to allow users to scroll through the different categories of news articles.

## Architecture
![Architecture](https://www.nexmobility.com/articles/images/mvvm-architecture-pattern.png)

## Screenshots
<p align="middle">
  <img src="Screenshot_1598719096.png?raw=true" width="250" />
  <img src="Screenshot_1598719122.png?raw=true"   width="250"/> 
  <img src ="Screenshot_1598719302.png?raw=true"  width="250" />
</p>

## Gifs
<p align="middle">
  <img src="ScreenRecordingOpening.gif?raw=true" width="250" />
  <img src="ScreenRecordingScroll.gif?raw=true"   width="250"/> 
   <img src ="ScreenRecordingWebOpening.gif?raw=true"  width="250" />
  <img src ="ScreenRecordingRefresh.gif?raw=true"  width="250" />
</p>


## Steps to run the App
- Clone or import the project into Android Studio
- Create your API key at [News API](https://newsapi.org/)
- Add the generated API key in `app/java/repositories/NewsAPI`file  
- Once in the NewsAPI interface file, replace the static final String variable titled "API_KEY", with your API KEY
- Build and run the app.


Thank you!
