import datetime

def show_menu():
    print('\nSmart Prescription Medicine Reminder System')
    print('1. Add prescription')
    print('2. Update prescription')
    print('3. Delete prescription')
    print('4. View all prescriptions')
    print('5. Search prescriptions')
    print('6. Show today\'s reminders & expiry alerts')
    print('7. Exit')

def get_user_choice():
    while True:
        choice = input('Enter the Choice between 1-7: ').strip()
        if not choice.isdigit():
            print('Enter the choice in digit format.')
            continue
        choice_int = int(choice)
        if 1 <= choice_int <= 7:
            return choice_int
        print('Choice must be between 1 and 7.')

def validate_date_format(date_text):
    parts = date_text.split('-')
    if len(parts) != 3:
        return False
    year, month, day = parts
    if not (year.isdigit() and month.isdigit() and day.isdigit()):
        return False
    if len(year) != 4 or len(month) != 2 or len(day) != 2:
        return False
    try:
        datetime.date(int(year), int(month), int(day))
        return True
    except ValueError:
        return False

def validate_time_format(time_text):
    parts = time_text.split(':')
    if len(parts) != 2:
        return False
    hour, minute = parts
    if not (hour.isdigit() and minute.isdigit()):
        return False
    hour_int = int(hour)
    minute_int = int(minute)
    return 0 <= hour_int <= 23 and 0 <= minute_int <= 59

def parse_times(times_input):
    times = [time.strip() for time in times_input.split(',') if time.strip()]
    valid_times = []
    for time_value in times:
        if validate_time_format(time_value):
            valid_times.append(time_value)
        else:
            print(f'Invalid time format ignored: {time_value}')
    return valid_times

def find_prescription_by_id(prescriptions, prescription_id):
    for prescription in prescriptions:
        if prescription['id'] == prescription_id:
            return prescription
    return None

def input_prescription_id(prescriptions):
    while True:
        prescription_id = input('Enter prescription ID: ').strip()
        if not prescription_id:
            print('Prescription ID cannot be empty.')
            continue
        if find_prescription_by_id(prescriptions, prescription_id):
            print('A prescription with that ID already exists. Enter a unique ID.')
            continue
        return prescription_id

def input_date(prompt):
    while True:
        date_text = input(prompt).strip()
        if validate_date_format(date_text):
            return date_text
        print('Invalid date format. Use YYYY-MM-DD.')

def input_times():
    while True:
        times_input = input('Enter reminder times separated by commas (HH:MM, HH:MM): ').strip()
        times = parse_times(times_input)
        if times:
            return times
        print('Enter at least one valid time.')

def print_prescription_details(prescription):
    today = datetime.date.today().isoformat()
    expiry = prescription.get('expiry_date', 'N/A')
    
    print('-' * 40)
    print(f"ID: {prescription['id']}")
    print(f"Patient: {prescription['patient']}")
    print(f"Medicine: {prescription['medicine']}")
    print(f"Dose: {prescription['dose']}")
    print(f"Reminder times: {', '.join(prescription['times'])}")
    print(f"Start date: {prescription['start_date']}")
    print(f"End date: {prescription['end_date']}")
    print(f"Expiry date: {expiry}")
    
    if expiry != 'N/A' and expiry <= today:
        print("⚠️  STATUS: MEDICINE IS EXPIRED!")

def add_prescription(prescriptions):
    print('\nAdd Prescription')
    prescription_id = input_prescription_id(prescriptions)
    patient = input('Enter patient name: ').strip()
    medicine = input('Enter medicine name: ').strip()
    dose = input('Enter dose information: ').strip()
    times = input_times()
    start_date = input_date('Enter start date (YYYY-MM-DD): ')
    end_date = input_date('Enter end date (YYYY-MM-DD): ')
    expiry_date = input_date('Enter medicine expiry date (YYYY-MM-DD): ')

    if start_date > end_date:
        print('Start date must be on or before end date. Prescription not added.')
        return

    prescription = {
        'id': prescription_id,
        'patient': patient,
        'medicine': medicine,
        'dose': dose,
        'times': times,
        'start_date': start_date,
        'end_date': end_date,
        'expiry_date': expiry_date
    }
    prescriptions.append(prescription)
    print('Prescription added successfully.')

def view_prescriptions(prescriptions):
    print('\nView All Prescriptions')
    if not prescriptions:
        print('No prescriptions available.')
        return
    for prescription in prescriptions:
        print_prescription_details(prescription)
    print('-' * 40)

def search_prescriptions(prescriptions):
    print('\nSearch Prescriptions')
    search_text = input('Enter patient name or medicine to search: ').strip().lower()
    if not search_text:
        print('Search text cannot be empty.')
        return

    matches = [p for p in prescriptions if search_text in p['patient'].lower() or search_text in p['medicine'].lower()]

    if not matches:
        print('No matching prescriptions found.')
        return

    for prescription in matches:
        print_prescription_details(prescription)
    print('-' * 40)

def update_prescription(prescriptions):
    print('\nUpdate Prescription')
    prescription_id = input('Enter prescription ID to update: ').strip()
    prescription = find_prescription_by_id(prescriptions, prescription_id)
    if not prescription:
        print('Prescription not found.')
        return

    print('Press Enter to keep the current value.')
    patient = input(f"Patient name [{prescription['patient']}]: ").strip()
    medicine = input(f"Medicine name [{prescription['medicine']}]: ").strip()
    dose = input(f"Dose information [{prescription['dose']}]: ").strip()
    times_input = input(f"Reminder times [{', '.join(prescription['times'])}]: ").strip()
    start_date = input(f"Start date [{prescription['start_date']}]: ").strip()
    end_date = input(f"End date [{prescription['end_date']}]: ").strip()
    expiry_date = input(f"Expiry date [{prescription.get('expiry_date', 'N/A')}]: ").strip()

    if patient:
        prescription['patient'] = patient
    if medicine:
        prescription['medicine'] = medicine
    if dose:
        prescription['dose'] = dose
    if times_input:
        new_times = parse_times(times_input)
        if new_times:
            prescription['times'] = new_times
        else:
            print('Invalid times entered. Existing times kept.')
    if start_date:
        if validate_date_format(start_date):
            prescription['start_date'] = start_date
        else:
            print('Invalid start date format. Existing start date kept.')
    if end_date:
        if validate_date_format(end_date):
            prescription['end_date'] = end_date
        else:
            print('Invalid end date format. Existing end date kept.')
    if expiry_date:
        if validate_date_format(expiry_date):
            prescription['expiry_date'] = expiry_date
        else:
            print('Invalid expiry date format. Existing expiry date kept.')

    if prescription['start_date'] > prescription['end_date']:
        print('After update, start date is after end date. Please update dates.')
        prescription['start_date'] = input_date('Enter a valid start date (YYYY-MM-DD): ')
        prescription['end_date'] = input_date('Enter a valid end date (YYYY-MM-DD): ')

    print('Prescription updated successfully.')

def delete_prescription(prescriptions):
    print('\nDelete Prescription')
    prescription_id = input('Enter prescription ID to delete: ').strip()
    prescription = find_prescription_by_id(prescriptions, prescription_id)
    if not prescription:
        print('Prescription not found.')
        return
    prescriptions.remove(prescription)
    print('Prescription deleted successfully.')

def show_today_reminders(prescriptions):
    print('\nToday\'s Reminders & Expiry Check')
    today = datetime.date.today().isoformat()
    found = False

    for prescription in prescriptions:
        # Active dosage schedule check
        is_active_schedule = prescription['start_date'] <= today <= prescription['end_date']
        # Check if medicine has passed or reached expiry date
        is_expired = prescription.get('expiry_date') and prescription['expiry_date'] <= today

        if is_active_schedule:
            found = True
            print('-' * 40)
            print(f"ID: {prescription['id']}")
            print(f"Patient: {prescription['patient']}")
            print(f"Medicine: {prescription['medicine']}")
            print(f"Dose: {prescription['dose']}")
            print(f"Reminder times today: {', '.join(prescription['times'])}")
            
            if is_expired:
                print(f"  WARNING: {prescription['medicine']} EXPIRED on {prescription['expiry_date']}! Do not consume.")
            else:
                print(f"Expiry Date: {prescription['expiry_date']}")

    if not found:
        print('No dose reminders scheduled for today.')
    else:
        print('-' * 40)

def pause():
    input('\nPress Enter to continue...')

def main():
    prescriptions = []
    while True:
        show_menu()
        choice = get_user_choice()
        if choice == 1:
            add_prescription(prescriptions)
        elif choice == 2:
            update_prescription(prescriptions)
        elif choice == 3:
            delete_prescription(prescriptions)
        elif choice == 4:
            view_prescriptions(prescriptions)
        elif choice == 5:
            search_prescriptions(prescriptions)
        elif choice == 6:
            show_today_reminders(prescriptions)
        elif choice == 7:
            print('Exiting program. Goodbye!')
            break
        pause()

if __name__ == '__main__':
    main()

