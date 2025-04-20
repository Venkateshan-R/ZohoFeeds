# Feed-Based Android Application

## Overview

This Android app demonstrates modern Android practices. It fetches a list of feed items from an API, 
shows them in a list, and displays more details when a feed item is tapped. 
The app also supports **swipe-to-refresh** for reloading the feed.

## Key Features

- **Feed List**: Displays feed items with a swipe-to-refresh option.
- **Detail Screen**: Shows detailed information fetched from the **Room Database**.
- **UI Design**: Built with **Jetpack Compose** and **Material3**.
- **State Management**: Uses **MVVM** architecture with **StateFlow** and **SharedFlow** for state handling.
- **Error Handling**: Handles network and other errors with clear feedback.

## Architecture
This app uses **MVVM (Model-View-ViewModel)** to separate the UI and business logic. The app has **data** and **ui** packages.

## Libraries Used
- **Jetpack Compose** for UI.
- **Hilt** for dependency injection.
- **Retrofit** for networking.
- **Room** for local storage.
- **Gson** for JSON parsing.
- **SwipeRefreshLayout** for refreshing data.
- **Coil** for image loading.

## Development Approach

1. **Architecture**: Used **MVVM** to separate UI and logic. Organized into **repository**, **viewmodel**  and **ui**.
2. **UI Design**: Built with **Jetpack Compose** and **Material3** for a modern look.
3. **State Management**: Used **StateFlow** and **SharedFlow** for smooth state handling.
4. **Repository**: Handles data fetching from API **Retrofit** and local storage using **Room Database**.
5. **Dependency Injection**: Managed dependencies with **Hilt**.
6. **Error Handling**: Used **sealed classes** for clear feedback on loading, success, and errors.

## Error Handling
The app handles loading, success, and error states using **sealed classes**. It shows appropriate messages based on the data-fetching status.

## Extension Functions
The app includes **extension functions** to improve code readability and reusability. These are in the **`utils`** package.
