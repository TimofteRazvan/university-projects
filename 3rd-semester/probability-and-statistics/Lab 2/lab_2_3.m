k = [0, 1, 2, 3];
n = input("Nr. of tries: ");
p = input("Probability of success: ");
c1 = binopdf(0, n, p);
c2 = 1 - binopdf(1, n, p);
d1 = binocdf(2, n ,p);
d2 = binocdf(1, n, p);
e1 = 1 - c1;
e2 = 1 - d2;
formatSpec = 'P(X %s) = %f\n';
fprintf(formatSpec, '= 1', c1)
fprintf(formatSpec, '!= 1', c2)
fprintf(formatSpec, '<= 2', d1)
fprintf(formatSpec, '< 2', d2)
fprintf(formatSpec, '>= 1', e1)
fprintf(formatSpec, '> 1', e2)
heads = 0;
for c = 1:3
    toss = rand();
    if toss > 0.5
        fprintf('Heads!\n')
        heads = heads + 1;
    else
        fprintf('Tails!\n')
    end
end
bpd = binopdf(heads, 3, 0.5);
fprintf('Heads flipped = %d\n', heads)
fprintf('Probability of flipping %d head(s) = %f\n', heads, bpd)