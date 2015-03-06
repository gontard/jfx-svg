#!/bin/bash

export XMLLINT_INDENT="    "
find . \( -name "*.fxml" -o -name "*.svg" \) -type f -exec xmllint --output '{}' --format '{}' \;
