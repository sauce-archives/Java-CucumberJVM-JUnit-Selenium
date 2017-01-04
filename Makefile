run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_windows_10_edge_14 test_windows_10_firefox_49 test_windows_7_ie_11 test_os_x_10_11_safari_10 test_os_x_10_10_chrome_54

test_windows_10_edge_14:
	mvn install -DbrowserName=MicrosoftEdge -Dversion=14.14393 -Dplatform="Windows 10" -Dsuitename=test_windows_10_edge_14

test_windows_10_firefox_49:
	mvn install -DbrowserName=firefox -Dversion=49.0 -Dplatform="Windows 10" -Dsuitename=test_windows_10_firefox_49

test_windows_7_ie_11:
	mvn install -DbrowserName=iexplore -Dversion=11.0 -Dplatform="Windows 7" -Dsuitename=test_windows_7_ie_11

test_os_x_10_11_safari_10:
	mvn install -DbrowserName=safari -Dversion=10.0 -Dplatform="OS X 10.11" -Dsuitename=test_os_x_10_11_safari_10

test_os_x_10_10_chrome_54:
	mvn install -DbrowserName=chrome -Dversion=54.0 -Dplatform="OS X 10.10" -Dsuitename=test_os_x_10_10_chrome_54