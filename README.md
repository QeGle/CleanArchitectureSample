# CleanArchitectureSample
Приложение получает данные из сети через API и отображает их на экране в виде списка. 
Собрано из https://bitbucket.org/ManuelMato/baseproject/ как пример Clean Architecture и Clean Code для последующих изменений.

# TODO
* Добавить пагинацию из Android Architecture Components
* "Подробности" по каждому элементу списка
* Кэширование данных при помощи Room


# Стек
* [Retrofit](https://square.github.io/retrofit/) - для загрузки данных
* [Glide](https://bumptech.github.io/glide/) - для получения и кэширования изображений
* [Koin](https://insert-koin.io/) - в качестве инструмента для реализации DI
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - в качестве инструмента для реализации асинхронной работы
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html) - для обновления состояния при изменении данных в базе
* [Mockito](https://site.mockito.org/) и [JUnit](https://junit.org/)- для тестирования . 
