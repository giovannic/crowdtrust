CONTEXT   = ../webapps/ROOT
CLSDIR    = $(CONTEXT)/WEB-INF/classes
CLASSES   = crowdtrust/TaskServlet.class
CLASSPATH = ../webapps/ROOT/WEB_INF/lib/servlet-api-3.0.jar

all: $(CLASSES)

clean:
	rm -f $(CLASSES) */*.class

install: $(CLASSES)
	install -m600 $(CLASSES) $(CLSDIR)

crowdtrust/TaskServlet.class: crowdtrust/TaskServlet.java
	javac -classpath $(CLASSPATH) crowdtrust/TaskServlet.java
