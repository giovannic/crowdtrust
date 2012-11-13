CONTEXT   = webapps/ROOT
SRCDIR    = src

CLASSDIR  = $(CONTEXT)/WEB-INF/classes
LIBDIR    = $(CONTEXT)/WEB-INF/lib
CLASSPATH = $(LIBDIR)/*.jar

JAVAC     = javac

SOURCES   = $(wildcard $(SRCDIR)/crowdtrust/*.java)
CLASSES   = $(SOURCES:.java=.class)

all: install clean

clean:
	rm -f $(CLASSES)

install: $(CLASSES)
	install -m600 $(CLASSES) $(CLASSDIR)/crowdtrust

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) $<

