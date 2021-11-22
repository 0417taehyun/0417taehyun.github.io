import tracemalloc

tracemalloc.start()

snapshot1 = tracemalloc.take_snapshot()

test_list = [1, 'test1', 2, 'test2']

snapshot2 = tracemalloc.take_snapshot()

statuses = snapshot2.compare_to(snapshot1, 'lineno')

for status in statuses:
    print(status)