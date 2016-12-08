run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_windows_10_edge_14 test_windows_10_firefox_49 test_windows_7_ie_11 test_os_x_10_11_safari_10 test_os_x_10_10_chrome_54
	
test_windows_10_edge_14:
	browserName=MicrosoftEdge version=14.14393 platform="Windows 10" mvn install

test_windows_10_firefox_49:
	browserName=firefox version=49.0 platform="Windows 10" mvn install

test_windows_7_ie_11:
	browserName=iexplore version=11.0 platform="Windows 7" mvn install

test_os_x_10_11_safari_10:
	browserName=safari version=10.0 platform="OS X 10.11" mvn install

test_os_x_10_10_chrome_54:
	browserName=chrome version=54.0 platform="OS X 10.10" mvn install