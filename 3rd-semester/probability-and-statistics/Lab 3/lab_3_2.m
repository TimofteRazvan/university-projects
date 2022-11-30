success = input('Probability of success: ');
if success < 0.05 || success > 0.95
    fprintf('Incorrect success probability!\n');
end
for n = 1:3:100
    mu = n*p;
    sigma = sqrt(n*p*(1-p));
    grid on
    x = 0:n;
    y1 = binopdf(x,n,p);
    y2 = normpdf(x,mu,sigma);
    plot(x,y1,x,y2);
    title('First Problem')
    pause(0.5);
end

