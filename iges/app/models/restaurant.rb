class Restaurant < ActiveRecord::Base
	has_many :thoughts
	validates :title, presence: true,
                    length: { minimum: 1 }
end
