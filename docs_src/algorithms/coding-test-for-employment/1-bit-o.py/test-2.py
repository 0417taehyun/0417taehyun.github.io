import time

start_time = time.time()

result = 0

for num in range(1, 10):
    result += num

end_time = time.time()

print('time: ', end_time - start_time)