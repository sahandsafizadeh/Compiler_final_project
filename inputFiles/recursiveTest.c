function int fact(int n);

function int fib(int n);

function int main() begin
    int f = fact(6);
    int f1 = fib(6);
    print(f);
    print(f1);
end

function int fact(int n) begin
    if(n <= 1)
    begin
        return 1;
    end
    else
    begin
        return n * fact(n - 1);
    end
end

function int fib(int n) begin
    if(n <= 1)
    begin
        return n;
    end
    else
    begin
        return fib(n - 1) + fib(n - 2);
    end
end