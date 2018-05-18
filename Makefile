run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j windows_10_edge windows_8_ie mac_sierra_chrome windows_7_ff

windows_10_edge:
	mvn install -Dplatform=windows_10_edge

windows_8_ie:
	mvn install -Dplatform=windows_8_ie

mac_sierra_chrome:
	mvn install -Dplatform=mac_sierra_chrome

windows_7_ff:
	mvn install -Dplatform=windows_7_ff
