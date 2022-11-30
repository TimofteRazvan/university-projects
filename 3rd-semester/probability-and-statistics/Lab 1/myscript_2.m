x=linspace(0,3);
f1=(x.^5)/10;
f2=x.*sin(x);
f3=cos(x);

plot(x, f1, '--r','LineWidth', 3)
hold on
plot(x, f2, '-.g','LineWidth', 3)
plot(x, f3, ':b','LineWidth', 3)
hold off
grid