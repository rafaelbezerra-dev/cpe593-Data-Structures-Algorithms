
y = list_size;
x = [ millis10 millis50 millis100 ];
bar(y, x);
title('Build List Benchmark');
xlabel('List Size')
ylabel('Milliseconds')
legend('10 prime multiples','50 prime multiples','100 prime multiples','Location','northwest')