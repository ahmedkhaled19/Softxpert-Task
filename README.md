# Softxpert-Task

# My Implementation Description

- Android Clean Application Architecture Based on layers (Modularied) and every screen (Logic) represents a feature module
- MVVM pattern for the packaging and the code with flowing the rules of clean code
- Kotlin as the base language for the app
- Converted Gradle to be kotlin-dsl 
- For the UI, I used viewBinding - as dataBinding is obsolete has been replaced - with ConstraintLayout and Material Design Components
- Handled fragments by using Jetpack Navigation Component
- Kotlin Coroutines for asynchronous operations and keep things away from Main Thread
- Retrofit for API communication.
- Dagger Hilt for providing objects over the app
- Mapper to convert remote data(comes from API) to local data(The real data I want to show) and make sure that it is ready to show to the user and without null data
- Glide for loading data from the URL 
- Junit4 for the unit test (retrofit and mapper)
