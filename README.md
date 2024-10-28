# MovieCheckerApp 🎬

MovieCheckerApp is a Java application that uses the TMDb API to access information about movies, including currently playing movies in theaters.

## Features

- **Search Movies by Title**: Enter a title, and the app will display movies with that title, including details like release date, genre, and overview.
- **Browse Currently Playing Movies**: Using the graphical interface (GUI), you can view all movies currently playing in theaters, filtered by genre.

## How It Works

- **API Integration**: The app connects to the TMDb (The Movie Database) API to fetch up-to-date movie information.
- **Graphical Interface**: The JavaFX-based GUI allows users to select a genre and see movies currently playing in theaters within that genre.

## Setup Instructions

1. **API Key**: This app requires a TMDb API key.
   - Set up an environment variable `TMDB_API_KEY` with your API key.
2. **Run the App**: Launch the application to start searching for movies or use the GUI to explore currently playing movies by genre.

## Example Usage

1. **Search by Title**:
   - Enter "Batman" and see all movies with that title, along with release date, genre, and overview.

2. **Browse by Genre**:
   - Open the GUI, select a genre (e.g., Action), and see all movies currently playing in theaters within that genre.
