<img src="https://github.com/AlterJuice/JGiphyApp/blob/V1_85/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png"> 

# JGiphyApp


### Android Java app using Giphy REST API for Natife company. Task:
```

Тестовое задание Android.
 Необходимо создать приложение с использованием giphy REST api https://developers.giphy.com
 
 Пример запроса https://api.giphy.com/v1/gifs/search?api_key=API_KEY&q=&limit=25&offset=0&rating=g&lang=en
 Приложение должно состоять из двух экранов:
  - Функционал первого экрана: Отображение gif изображений в виде списка/таблицы
  - Функционал второго экрана: Полноэкранное отображение выбранной гифки
 
 Для выполнения запросов к api можно использовать библиотеку Retrofit.
 
 Будет дополнительным плюсом если:
 - Приложение будет учитывать переворот экрана;
 - Будет использоваться  MVP, MVVM, MVI... паттерн
 - Использовать Dependency injection (Dagger, Koin...)
 - Желательно писать на Kotlin
 - Будет хорошим плюсом использование RxJava2, kotlin coroutines, Android architecture components.
 - Вести разработку в репозитории, по окончанию работы - предоставить ссылку на репо.
```
<hr>
<!--  -->

### Using stack:
 - Android SDK
 - Java
 - Android Architecture Components
 - Pattern MVVM + Repo
 - ViewBinding
 - Retrofit2
 - Gson
 - Glide/Picasso
- - - -
> ## v1.85. Android6.0+. Added Icon and advanced Copy&Share links' methods. Supporting UNDO copy actions with SnackBar.
> <div>
> <img src="https://user-images.githubusercontent.com/32757860/121816525-d8cd1380-cc84-11eb-8af7-c506c1d18994.png" width="40%" > 
> <img src="https://user-images.githubusercontent.com/32757860/121816786-4594dd80-cc86-11eb-8239-9d78956de357.png" width="40%" >
> <img src="https://user-images.githubusercontent.com/32757860/121816843-9f95a300-cc86-11eb-9c44-3f373af21542.png" width="35%" >
> <img src="https://user-images.githubusercontent.com/32757860/121814566-65be9f80-cc7a-11eb-8359-9394520485d4.png" width="50%" >
> </div>
- - - -
> ## v1.7 - A bit optimized version of the application by removing unused data with animated preview (as it was long ago).
> <img src="https://user-images.githubusercontent.com/32757860/120251667-efd63380-c28a-11eb-8d25-cbc5201a18b4.gif" height="600px">
- - - -
> ## v1.5. Android8.0+. First stable release.
> ### The portrait view of application. Detailed gif-fragment with 4-5 buttons (Back, copy link, download, author page, gif page) (Gif 1). Buttons also support tooltip texts (Gif 2).
> <img src="https://user-images.githubusercontent.com/32757860/120233011-77a54900-c25d-11eb-9d2b-afa807bb7897.gif" height="600px">
> <img src="https://user-images.githubusercontent.com/32757860/120233208-eedadd00-c25d-11eb-9e65-98b73706008c.gif" height="600px">
> 
> ### JGiphy supports the searching gifs via SearchView in the top AppBar, just put yout search query here (Gif 1). By the way you can download gifs with original size from web-page via "Download" button (Gif 2).
> <img src="https://user-images.githubusercontent.com/32757860/120233220-f69a8180-c25d-11eb-8080-f90be732ce39.gif" height="600px">
> <img src="https://user-images.githubusercontent.com/32757860/120233266-1762d700-c25e-11eb-9bd4-af72a98b8027.gif" height="600px">
>
> ### Rotation from Portrait to Landscape view without loss and leaks of memmory (ViewModels in work):
> <img src="https://user-images.githubusercontent.com/32757860/120233609-e7680380-c25e-11eb-942c-3c448f0d185a.gif" height="600px">
> <img src="https://user-images.githubusercontent.com/32757860/120233333-3fead100-c25e-11eb-8e1e-3b127c55d13c.gif" width="60%">
>

