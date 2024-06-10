BUILD_DIR=lox/build
LOX_DIR=lox

all : Lox.class

clean :
        rm $(BUILD_DIR)/TokenType.java
        rm $(BUILD_DIR)/Token.java
        rm $(BUILD_DIR)/Scanner.java
        rm $(BUILD_DIR)/Lox.java

Lox.class : TokenType.class Token.class Scanner.class
        javac $(LOX_DIR)/Lox.java -d $(BUILD_DIR)/

TokenType.class :
        javac $(LOX_DIR)/TokenType.java -d $(BUILD_DIR)/

Token.class :
        javac $(LOX_DIR)/Token.java -d $(BUILD_DIR)/

Scanner.class :
        javac $(LOX_DIR)/Scanner.java -d $(BUILD_DIR)/