require 'yelp'

def random_place(places)	
	places = shuffle(places)
	i = 0 # index of places
	
	loop do
		puts "Go eat at " + places[i].name
		puts "Phone: " + places[i].display_phone
		puts "Rating: " + places[i].rating.to_s
		puts places[i].location.display_address
		puts places[i].url 
		
		puts "Don't like it? Press enter to get another place or type n to quit."
		ans = gets.chomp
		i = i + 1
		# shuffles same 40 cause there's bound to be something good there
		if i == places.length
			places = shuffle(places)
			i = 0
		end
		
		break if ans == "n"
	end
end

# shuffles by swapping with randomly chosen index
def shuffle(a)
	for i in 0..(a.length - 1)
		j = rand(i)
		temp = a[j]
		a[j] = a[i]
		a[i] = temp
	end
	a
end

# Yelp API stuff
client = Yelp::Client.new({ consumer_key: 'NVaDEK37pXM5ZGRfnN3NRg',
                            consumer_secret: 'RgH-3tzg5URWf-hK8G3NA8anG-s',
                            token: 'V2CM0wuGT55RQ-QNzRzpLsKNZElNMThW',
                            token_secret: 'ddiuTnligH82cESsnS_TNi6xHh8'
                          })
# parameters for searches
params = { term: 'food',
					 limit: 20,
					 sort: 1
				 }

puts "Welcome to the Random Restaurant Generator"
puts "Please enter your location (Zip Code, City)"
location = gets.chomp

responses1 = client.search(location, params)
responses2 = client.search(location, params, {offset: 20}) # gets additional 20
random_place(responses1.businesses + responses2.businesses)	