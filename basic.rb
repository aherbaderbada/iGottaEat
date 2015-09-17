def everything
	puts "Welcome to the Random Food Generator you very kind and generous individual."
	puts "Please manually enter the places you would like to eat.\nType whatever when done."
	
	place = gets.chomp
	places = []
	places[0] = "Don't just put whatever as the only place to eat."
	i = 0
	while place != "whatever" do
		places[i] = place
		i = i+1
		place = gets.chomp
	end
	
	places = shuffle(places)
	i = 0
	loop do
		puts "Go eat at " + places[i]
		puts "Don't like it? Press any key to get another place or type n to quit."
		ans = gets.chomp
		i = i + 1
		if i == places.length
			places = shuffle(places)
			i = 0
		end
		break if ans == "n"
	end
end

def shuffle(a)
	for i in 0..(a.length - 1)
		j = rand(i)
		temp = a[j]
		a[j] = a[i]
		a[i] = temp
	end
	a
end

everything
	
