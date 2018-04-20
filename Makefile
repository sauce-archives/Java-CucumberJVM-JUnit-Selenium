run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j windows_10_edge windows_8_ie mac_sierra_chrome windows_7_ff

windows_10_edge:
	PLATFORM=windows_10_edge mvn install

windows_8_ie:
	PLATFORM=windows_8_ie mvn install

mac_sierra_chrome:
	PLATFORM=mac_sierra_chrome mvn install

windows_7_ff:
	PLATFORM=windows_7_ff mvn install
