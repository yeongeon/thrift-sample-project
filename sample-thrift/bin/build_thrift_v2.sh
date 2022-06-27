#!/usr/bin/env bash

TARGET_PROJECT="sample"
TARGET_VERSION="v2"

PWD=`pwd`
OUTPUT="$PWD"
ARRAY=(
#  "java:generated_annotations=suppress":"$OUTPUT/src/main"
  "go":"$OUTPUT/src/main"
)

for item in "${ARRAY[@]}";
do
entry="${item%%:*}"
filetype="${item%:*}"
out="${item##*:}"
echo "entry: $out"
  if [ ! -d "$out" ]
  then
    mkdir $out
    echo "[mkdir] $out was done."
  fi
    if [ ! -d "$out/$entry" ]
    then
      mkdir $out/$entry
      echo "[mkdir] $out/$entry was done."
    fi
      for idl in $PWD/src/main/thrift/$TARGET_PROJECT/$TARGET_VERSION/*.thrift
      do
        thrift --gen $filetype -out $out/$entry -v $idl
      done
done

exit 0;