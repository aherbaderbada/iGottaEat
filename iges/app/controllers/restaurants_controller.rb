class RestaurantsController < ApplicationController					
	# def new
		# @restaurant = Restaurant.new
	# end
		
	def create
		@restaurant = Restaurant.new(restaurant_params)
		if !@restaurant.save
			render 'index'
		end
		
		location = params[:title]
		
		# Yelp API stuff
		client = Yelp::Client.new({ consumer_key: 'NVaDEK37pXM5ZGRfnN3NRg',
																consumer_secret: 'RgH-3tzg5URWf-hK8G3NA8anG-s',
																token: 'V2CM0wuGT55RQ-QNzRzpLsKNZElNMThW',
																token_secret: 'ddiuTnligH82cESsnS_TNi6xHh8'
															 })
		# parameters for searches
		p = { term: 'food',
					 limit: 20,
					 sort: 1
				 }
		# generates and shuffles 40 restaurants near location
		responses1 = client.search(location, p)
		responses2 = client.search(location, p, {offset: 20})
		@place = shuffle(responses1.businesses + responses2.businesses)
			
		@i = 0 # index for @place
		render 'show'
	end
	
	# def show
		# @restaurant = Restaurant.find(params[:location])
	# end
	
	def shuffle(a)
		for i in 0..(a.length - 1)
			j = rand(i)
			temp = a[j]
			a[j] = a[i]
			a[i] = temp
		end
		a
	end
	helper_method :shuffle
	
	private
		def restaurant_params
			params.permit(:title)
		end
end

