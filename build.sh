rm -r bin/*
cd src
find . -name "*.java" -print | xargs javac -g
find . -name '*.class' -exec cp --parents \{\} ../bin \;
#find . -type f -name "*.class" -print0 | xargs -0 -Imyclasses mv -i myclasses ../bin
find . -name "*.class" -type f -delete
cd -
