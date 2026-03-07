#  TurboLog v2.0

**TurboLog** is a high-performance Java tool designed to **generate massive log files and analyze them at ultra-fast speed**.

It demonstrates how large log files (GB scale) can be processed efficiently using:

* Java **NIO**
* **Memory-Mapped Files**
* **ForkJoin parallel processing**
* Optimized **8MB buffering**

---

#  New in Version 2.0

*  **Disk Space Guard** – prevents log generation if storage is insufficient
*  **Interactive CLI** – user inputs log size and search keywords
*  **Optimized 8MB Buffer** for high-speed log writing
*  **Improved analyzer performance**

---

#  Project Structure

```
TurboLog/
│
├── LogGenerator.java
├── UltraFastLogAnalyzer.java
└── README.md
```

---

#  Components

## 1️ LogGenerator

Creates a large log file named:

```
test.log
```

### Features

* User chooses **log size in GB**
* Uses an **8MB buffer** for faster file writing
* Inserts repeated log entries containing `CRITICAL_ERROR`
* Includes a **Disk Space Guard** to prevent failures

### Example Log Entry

```
INFO 2026-02-19 CRITICAL_ERROR: Failure
```

### Example Run

```
Size in GB: 1
Dynamic Log Created.
```

---

## 2️ UltraFastLogAnalyzer

Scans the generated `test.log` file and searches for a given keyword.

### Key Techniques

* **Memory-Mapped File Access** using `FileChannel`
* **Parallel searching** with `ForkJoinPool`
* File splitting into **multiple search tasks**
* Returns **match count and execution time**

### Example Run

```
Search Word: CRITICAL_ERROR
found 25000000 in 820 ms
```

---

#  How to Run

## Step 1 — Compile the Generator

```bash
javac LogGenerator.java
```

## Step 2 — Generate the Log File

```bash
java LogGenerator
```

Enter the desired log size:

```
Size in GB: 1
```

This creates:

```
test.log
```

---

## Step 3 — Compile the Analyzer

```bash
javac UltraFastLogAnalyzer.java
```

## Step 4 — Run the Analyzer

```bash
java UltraFastLogAnalyzer
```

Enter the keyword to search:

```
Search Word: CRITICAL_ERROR
```

Example output:

```
found 25000000 in 820 ms
```

---

#  Technologies Used

* **Java NIO**
* **FileChannel**
* **Memory Mapped Files**
* **ForkJoinPool**
* **ByteBuffer Optimization**
* **Parallel Processing**

---

# Project Goal

TurboLog is built to demonstrate **high-performance log processing in Java**, showing how extremely large log files can be **generated and scanned efficiently** using modern Java I/O techniques.

---

#  Version

**TurboLog v2.0**

Improvements over v1:

* Dynamic log size generation
* Disk space safety check
* Interactive command-line interface
* Optimized 8MB buffer writing
* Faster parallel log analysis
