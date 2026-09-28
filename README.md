# Text Analysis App

A Java console application that analyzes two text files and compares their content using text statistics, simple sentiment analysis, and cosine similarity.

## Features

- Tokenizes and normalizes text
- Filters common stopwords
- Counts words, sentences, paragraphs, and characters
- Calculates vocabulary richness
- Finds frequent and long words
- Counts vowels and consonants
- Performs sentiment analysis using word lists
- Displays the 20 most frequent words
- Compares two documents using cosine similarity
- Saves analysis reports to text files

## Technologies and Concepts

Java, programming with objects, interfaces, collections, maps, file I/O, text processing, frequency analysis, vector representation, and cosine similarity.

## Project Structure

```
text-analysis-app/
├── src/
│   ├── Analysis.java
│   ├── FileHandler.java
│   ├── LiteraryAnalysis.java
│   ├── ReportGenerator.java
│   ├── SimilarityCalculator.java
│   ├── TextAnalysisApp.java
│   └── TextProcessor.java
├── stopwords.txt
├── positive.txt
├── negative.txt
├── .gitignore
└── README.md
```

## Run

```bash
javac -d out src/*.java
java -cp out TextAnalysisApp
```

Enter paths to two text files when prompted. The program prints both analyses, calculates cosine similarity, and generates `analysis_report_1.txt` and `analysis_report_2.txt`.

## Sentiment Analysis

The sentiment feature is intentionally simple: it compares token matches against `positive.txt` and `negative.txt`. The included word lists can be expanded for greater coverage.

## About

This project demonstrates Java OOP and practical text processing concepts while separating file handling, analysis, similarity calculation, and report generation into focused classes.
