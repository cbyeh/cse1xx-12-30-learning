BORT="Skipping $file."
SUCCESS="Done. Navigate to \`~/homework/$1\` to get started."
SRC_DIR="$PUBLIC/homework/$1"
DEST_DIR="$HOME/homework/$1"
FILE="myHW"

# Here are a couple of steps to get started on the script:
# Handle the incorrect number of parameters below:
if [ $# -ne 1 ]; then
        echo "$USAGE"
        exit 1
fi

# Handle inputs not existing in the public directory below:
if [ ! -d $SRC_DIR ]; then
        echo "$EINVALID"
        exit 1
fi

# Create the new directory if it doesn't exist yet
# 1. Iterate through the files in the source directory
if [ ! -d $DEST_DIR ]; then
        mkdir "$DEST_DIR"
fi

#    a) If a file does exist in the new directory, ask the user if
#       they want to overwrite each file.
for file in ls "$SRC_DIR"
do
        FILE=$DEST_DIR/$file
        PROMPT="$FILE already exists. Overwrite? (y/N)? "
        ABORT="Skipping $FILE. "
        if [ -f "$DEST_DIR/$file" ]; then
                while [[ "$userInput" != "y" || "$userInput" != "N" ]]
                do
                        read -n1 -p "$PROMPT" userInput
                        if [[ "$userInput" = 'y' || "$userInput" = 'Y' ]]; then
#    b) Otherwise just copy the file
                                cp -rf "$SRC_DIR/$file" "$DEST_DIR/$file"
                                echo
                                break
                        else
                                if [ "$userInput" = '' ]; then
                                        echo "$ABORT"
                                        break
                                else
                                        echo
                                        echo "$ABORT"
                                        break
                                fi
                        fi
                done
        else
                cp "$SRC_DIR/$input" "$DEST_DIR"
        fi
done