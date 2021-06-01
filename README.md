# JGiphyApp
### Android Test Java App using giphy REST API for Natif company. Text of Task:

<hr>

Тестовое задание Android.
 Необходимо создать приложение с использованием giphy REST api https://developers.giphy.com
 
 Пример запроса https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=&limit=25&offset=0&rating=g&lang=en
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

<hr>

> ## v1.7 
> ### version with animated preview (as it was long ago) and a bit optimezed
> ![ScreenRecord-20210601-033312](https://user-images.githubusercontent.com/32757860/120251667-efd63380-c28a-11eb-8d25-cbc5201a18b4.gif)

> ## v1.5
> ### Showing Portrait view of app. Gif detailed fragment with 4-5 buttons.
> ![ScreenRecord: Portrait ](https://user-images.githubusercontent.com/32757860/120233011-77a54900-c25d-11eb-9d2b-afa807bb7897.gif)

> ### Buttons with tooltip texts
> ![ScreenRecord-20210531-211832](https://user-images.githubusercontent.com/32757860/120233208-eedadd00-c25d-11eb-9e65-98b73706008c.gif)

> ### Searching new gifs
> ![ScreenRecord-20210531-212008](https://user-images.githubusercontent.com/32757860/120233220-f69a8180-c25d-11eb-8080-f90be732ce39.gif)

> ### Copying link and download gif with original size
> ![ScreenRecord-20210531-212050](https://user-images.githubusercontent.com/32757860/120233266-1762d700-c25e-11eb-9bd4-af72a98b8027.gif)

> ### Landscape view
> ![ScreenRecord-20210531-215602](https://user-images.githubusercontent.com/32757860/120233333-3fead100-c25e-11eb-8e1e-3b127c55d13c.gif)

> ### From portrait to landscape
> ![ScreenRecord-20210531-222134](https://user-images.githubusercontent.com/32757860/120233609-e7680380-c25e-11eb-942c-3c448f0d185a.gif)
