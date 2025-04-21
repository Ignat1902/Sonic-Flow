# Тестовое задание для стажёра Android-направления. Приложение Sonic-Flow.

## Описание

Был реализован один экран: экран со списком треков из чартов, с функцией поиска по трекам.  
Экран соответствует фукнкциональным и нефункциональным требованиям.  
Загрузка данных происходит из внешнего API Deezer.com.
При разработке архитектуры было решено следовать правилам Clean Architecture.  
Используемый архитектурный паттерн: MVVM.  
Приложение поддерживает светлую и тёмную темы.  

## Архитектура приложения выглядит следующим образом:
![Схема архитектуры](screenshots/architecture.jpg)

## APK для проверки

Скачать .apk файл для запуска на устройстве можно [ссылке](https://disk.yandex.ru/d/aGDdISktofwX-Q)

## Стек технологий

- Язык: kotlin
- Работа с сетью: Retrofit, OkHttp
- Многопоточность: Kotlin Coroutines/Flow
- View: XML
- Сериализаторы: Kotlinx Serialization
- Навигация: Fragment
- Архитектура: MVVM
- Загрузка изображений: Glide

## Скриншоты работы приложения

### Список треков из чарта

<p align="center">
  <img src="screenshots/chart_list_light_theme.jpg" alt="Список треков (Светлая тема)" width="45%"/>
  <img src="screenshots/chart_list_dark_theme.jpg" alt="Список треков (Тёмная тема)" width="45%"/>
</p>

### Поиск по трекам
<img src="screenshots/search_track.jpg" alt="Поиск по трекам" width="45%" />

### Состояние загрузки
<img src="screenshots/loading_state.jpg" alt="Загрузка" width="45%" />

### Сообщение об ошибке

<p align="center">
  <img src="screenshots/error.jpg" alt="Описание ошибки" width="45%"/>
  <img src="screenshots/error_try.jpg" alt="Диалоговое окно об ошибке" width="45%"/>
</p>

## Примечания:

Я столкнулся с несколькими трудностями при реализации медиа плеера, включая управление состоянием плеера, обработку метаданных треков и взаимодействие с медиа-сервисом.
Эти вопросы требовали дополнительных исследований и тестирования, что ограничило время на завершение всех задуманных функций.
В ветке [features/music-player](https://github.com/Ignat1902/Sonic-Flow/tree/features/music-player/features/music-player/src/main/java/dev/ginger/music/player) можно посмотреть мои наработки.




