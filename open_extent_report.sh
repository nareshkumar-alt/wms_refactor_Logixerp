#!/bin/bash
# Script to open the latest ExtentReport

REPORTS_DIR="./reports"
LATEST_REPORT=""

# Check if reports directory exists and has HTML files
if [ -d "$REPORTS_DIR" ]; then
    # Find the latest HTML report
    LATEST_REPORT=$(find "$REPORTS_DIR" -name "Test-Report-*.html" -type f -printf '%T@ %p\n' 2>/dev/null | sort -n | tail -1 | cut -d' ' -f2-)
    
    # Alternative method if find -printf doesn't work
    if [ -z "$LATEST_REPORT" ]; then
        LATEST_REPORT=$(ls -t "$REPORTS_DIR"/*.html 2>/dev/null | head -1)
    fi
fi

# If report found, open it
if [ -n "$LATEST_REPORT" ] && [ -f "$LATEST_REPORT" ]; then
    echo "Opening latest ExtentReport: $LATEST_REPORT"
    # Try different methods to open the file based on the system
    if command -v xdg-open > /dev/null; then
        xdg-open "$LATEST_REPORT"
    elif command -v open > /dev/null; then
        open "$LATEST_REPORT"
    elif command -v firefox > /dev/null; then
        firefox "$LATEST_REPORT" &
    elif command -v google-chrome > /dev/null; then
        google-chrome "$LATEST_REPORT" &
    else
        echo "Please open manually: $LATEST_REPORT"
        echo "Full path: $(readlink -f "$LATEST_REPORT")"
    fi
else
    echo "No ExtentReports found in $REPORTS_DIR"
    echo "Run tests first to generate ExtentReports."
    echo ""
    echo "To generate reports, run:"
    echo "  mvn test -Dsurefire.suiteXmlFiles=master.xml"
fi

