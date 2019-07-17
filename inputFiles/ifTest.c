function int main() begin
    const int cond_int = 100;
    int array[2];
    array[1] = 99;
    int cond = ('\t' == '\t') & (cond_int >= array[1]) | false;

    if(cond)
    begin
        if(cond_int == 100)
        begin
            string str = "hello";
            int expr = len(str) + 10;
            print(expr);
        end
    end
    else
    begin
        if(array[1] == 100)
        begin
            long l = 100;
            print(l);
        end
        else
        begin
            double d = 99e-1;
            array[1] = d;
            print(array[1]);
        end
    end
end