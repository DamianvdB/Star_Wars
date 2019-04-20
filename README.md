# Star Wars films
Android application written in Kotlin which displays information about all the released Star Wars films to date.
<br/><br/>

# Jetpack Components
The following Android Jetpack Components were used:
1. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
2. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
3. [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
<br/><br/>

# Application structure
### 1. Splash screen
Loads (and caches) all the basic information about the Stars Wars films.
<br/><br/>
<img src="https://raw.githubusercontent.com/DamianvdB/Star_Wars/master/art/film_splash_screen.jpg" width="300" height="600">
<br/><br/><br/>

### 2. Film list screen
Displays a list of all the Star Wars films, with the following information:
* Title
* Release date
* Directors
* Producers
* Poster
<br/><br/>
<img src="https://raw.githubusercontent.com/DamianvdB/Star_Wars/master/art/film_list_view_1.jpg" width="300" height="600">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/DamianvdB/Star_Wars/master/art/film_list_view_2.jpg" width="300" height="600">
<br/><br/><br/>

### 3. Film detail screen
Displays the following additional information about a specific Star Wars film:
* Rating
* Characters
* Crawling text
<br/><br/>
<img src="https://raw.githubusercontent.com/DamianvdB/Star_Wars/master/art/film_detail_view.jpg" width="300" height="600">
<br/><br/><br/>

# Resources
All information about the Star Wars films are from the following APIs:
* www.swapi.co
* www.themoviedb.org
