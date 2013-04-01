把win32com.dll拷贝到{JAVA_HOME}\jre\bin
把javax.comm.properties拷贝到{JAVA_HOME}\jre\lib
把comm.jar拷贝到{JAVA_HOME}\jre\lib\ext

set CLASSPATH={JAVA_HOME}\jre\lib\ext\comm.jar;%classpath%
