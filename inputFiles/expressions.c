record Taghi begin
    const double taghi_double=1.123;
    char taghi_char =  '\n';
    const char taghi_char1 = 't';
    bool is_taghi;
    int taghi_integer;
end record;

function int main() begin
    Taghi t1, t2;
    t1.is_taghi = true;
    t2.is_taghi = t1.is_taghi;
    print(t2.is_taghi);

    int test = 10;
    int arithmatic_expr = t1.taghi_double * 3 / 2 + (t2.is_taghi - test++) % 3;##1
    double f = 12.1;
    double f2 = f * 2;
    print(f2);
    print(arithmatic_expr);

    int res = 2 & 3;
    print(res);

    bool logical = (t1.is_taghi and (t2.is_taghi < 10)) or (t1.taghi_char == 'a');
    print(logical);
end