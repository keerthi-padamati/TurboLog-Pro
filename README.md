# Ultra Fast Log Analyzer

A lightweight Java project to **generate large log files** and **analyze them at high speed** using Java NIO memory-mapped files and parallel processing.

## Components

### 1. LogGenerator
Creates a large file named: ### test.log
- Generates 1,000,000 log entries  
- Inserts a `CRITICAL_ERROR` periodically  
- Used for performance testing

### 2. UltraFastLogAnalyzer
- Scans `test.log`
- Searches for the keyword: CRITICAL_ERROR
- Uses:
  - Memory-Mapped I/O (FileChannel)
  - ForkJoinPool for parallel search
- Outputs total match count

## How to Run

### Step 1 — Generate the log file
```bash
javac LogGenerator.java
java LogGenerator
```

### Step 2 — Analyze the log file
```bash
javac UltraFastLogAnalyzer.java
java UltraFastLogAnalyzer
```

### Output 
Log Created.
Matches: 20

