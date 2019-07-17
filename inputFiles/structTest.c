record Ali begin
    bool test;
    double str_double = 123.;
    int integer = 123;
end record;

function int main() begin
    Ali a;
    a.test = true;
    a.test *= 10;
    print(a.test);

    int expression = a.test + 3 * (10 - 5) % 2; ##11

    double darray[expression];
    int i;
    for(i = 1; i < expression; i += 1)
    begin
        darray[i] = 12.817;
        if(i == 9)
        begin
            int length = len("ali alavi");
            print(length);
            break;
        end
    end

    repeat
    begin
        print(darray[--i] + i);
    end
    until(i == 1);

    string str="hello world";
    print(a.integer);

end