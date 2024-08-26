About TalentWeather App



com.mohdgauri.talentweather    # Root Package
.
├── ApiClient           # For data handling.
│   ├── model           # Model classes
│   ├── network         # Remote Data Handlers     
|   │   ├── api         # Retrofit API for remote end point.
│   └── repository      # Single source of data.
|
|
├── ui                  # Activity/View layer
│   ├── main            # Main Screen Activity & ViewModel
|   │   ├── adapter     # Adapter for RecyclerView
|   │   ├── viewmodel   # ViewHolder for RecyclerView   
│   └── details         # Detail Screen Activity and ViewModel
|
└── utils               # Utility Classes / Kotlin extensions
