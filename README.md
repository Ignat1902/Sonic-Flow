# Приложение для прослушивания музыки Sonic-Flow.

## Описание

- Загрузка данных происходит из внешнего API Deezer.com.
- При разработке архитектуры было решено следовать правилам Clean Architecture.  
- Используемый архитектурный паттерн: MVVM. 
- Архитектура приложения многомодульная.
- Приложение поддерживает светлую и тёмную темы.


> [!WARNING]
> Экран аудиоплеера находится на стадии доработки.

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
