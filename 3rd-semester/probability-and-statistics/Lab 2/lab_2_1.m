k = [0, 1, 2, 3];
kc = 0:.1:3;
n = input("Nr. of tries: ");
p = input("Probability of success: ");
pd = binopdf(k, n, p);
cd = binocdf(kc, n, p);
matrix = [k; pd]
matrix = [kc; cd]