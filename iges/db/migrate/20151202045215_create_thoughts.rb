class CreateThoughts < ActiveRecord::Migration
  def change
    create_table :thoughts do |t|
      t.string :thinker
      t.text :body
      t.references :restaurant, index: true

      t.timestamps
    end
  end
end
