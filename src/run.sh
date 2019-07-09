find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java Avaj.simulator.Simulator scenario.txt
