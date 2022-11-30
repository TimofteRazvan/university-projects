success = input('Probability of success: ');
if success > 0.05
    fprintf('Incorrect success probability!\n');
end
for n = 30:3:100
    grid on
    x = 0:n;
    y1 = binopdf(x,n,p);
    y2 = poisspdf(x,n*p);
    plot(x,y1,x,y2);
    title('Second Problem')
    pause(0.5);
end

